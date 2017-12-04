"""
wiki.py
A very very simple Wiki

@author: Tobias Thelen
@contact: tobias.thelen@uni-osnabrueck.de
@licence: public domain
@status: completed
@version: 3 (10/2016)
"""

from server import webserver
from server.apps.static import StaticApp
import re
import os


class NoSuchPageError(Exception):
    """Raise if try to access non existant wiki page."""
    pass


class WikiApp(webserver.App):

    sitelist = os.listdir("./data")

    def register_routes(self):
        self.add_route("", self.show)
        self.add_route("show(/(?P<pagename>\w+))?", self.show)
        self.add_route("edit/(?P<pagename>\w+)", self.edit)
        self.add_route("save/(?P<pagename>\w+)", self.save)
        self.add_route("create", self.create)

    def read_page(self, pagename):
        """Read wiki page from data directory or raise NoSuchPageError."""

        try:
            with open("data/"+pagename, "r", encoding="utf-8", newline='') as f:
                x = f.read()
                return x
        except IOError:
            raise NoSuchPageError

    def markup(self, text):
        """Substitute wiki markup in text with html."""

        text = re.sub(r"<",
                      r"&lt;",
                      text)

        # substitute links: [[pagename]] -> <a href="/show/pagename">pagename</a>
        text = re.sub(r"\[\[([a-zA-Z0-9]+)\]\]",
                      r"<a href='/show/\1'>\1</a>",
                      text)

        # substitute headlines: !bang -> <h1>bang</h1>
        text = re.sub(r"^!(.*)$", r"<h1>\1</h1>", text, 0, re.MULTILINE)

        # replace two ends of line with <p>
        text = re.sub(r"\r?\n\r?\n", r"<p>", text)

        # replace one end of line with <br>
        text = re.sub(r"\r?\n\r?\n", r"<br>", text)

        return text

    def show(self, request, response, pathmatch=None):
        """Evaluate request and construct response."""
        self.sitelist = os.listdir("./data")
        try:
            pagename = pathmatch.group('pagename') or "main"
        except IndexError:
            pagename = "main"  # default pagename

        try:
            text = self.read_page(pagename)
        except NoSuchPageError:
            # redirect to edit view if page does not exist
            response.send_redirect("/edit/" + pagename)
            return

        # show page
        response.send_template('templates/wiki/show.html',
                                   {'text': self.markup(text),
                                   'pagename': pagename,
                                    "sitemap": self.sitelist})

    def edit(self, request, response, pathmatch=None):
        """Display wiki page for editing."""
        self.sitelist = os.listdir("./data")  # Making sure our filelist is recent

        try:
            pagename = pathmatch.group('pagename') or "main"
        except IndexError:
            pagename = "main"

        try:
            text = self.read_page(pagename)
        except NoSuchPageError:
            # use default text if page does not yet exist
            text = "This page is still empty. Fill it."

        # fill template and show
        response.send_template('templates/wiki/edit.html',
                                   {'text': text,
                                   'pagename': pagename,
                                    "sitemap" : self.sitelist, })

    def create(self, request, response, pathmatch=None):
        #create new page for editing or show existing one.
        self.sitelist = os.listdir("./data")  #Making sure our filelist is recent

        text = "Erstelle deine Seite!"
        pagename = request.params["sitename"] #Abfrage des Values aus dem Format!
        # FS: Das schlägt fehl, wenn nichts in der Textbox eingegeben wird.
        # Zudem fehlt hier eine Abfrage, ob pagename nur \w+ enthält. Ja, ich habe das pattern="..." gesehen,
        # allerdings gibt es in der Internet-Programierung die eine goldene Regel: Vertraue niemals dem Client. (Requests kann man fälschen...) -1P
        if pagename in self.sitelist:
            response.send_redirect("/edit/" + pagename)
        else:
            # fill template and show
            # Hier soll auf die /edit Seite weitergeleitet werden. -1P
            response.send_template('templates/wiki/edit.html',
                                   {'text': text,
                                   'pagename': pagename,
                                    "sitemap" : self.sitelist,})





    def save(self, request, response, pathmatch=None):
        """Evaluate request and construct response."""

        try:
            pagename = pathmatch.group('pagename')
        except IndexError:
            # no pagename given: error
            response.send_template("templates/wiki/wikierror.html",
                               {'error':'No pagename given.',
                                'text':'save action needs pagename'}, code=500)
            return

        try:
            wikitext = request.params['wikitext']
        except KeyError:
            # no text given: error
            response.send_template("templates/wiki/wikierror.html",
                               {'error':'No wikitext given.',
                                'pagename': 'Error', # FS: Von mir hinzugefügt.
                                'text':'save action needs wikitext'}, code=500)
                                # FS: Hier fehlt eine übergabe von "pagename". -1P
            return

        # ok, save text
        f = open("data/" + pagename, "w", encoding='utf-8', newline='')
        f.write(wikitext)
        f.close()
        #update sitelist

        response.send_redirect("/show/"+pagename)

if __name__ == '__main__':
    s = webserver.Webserver()
    s.add_app(WikiApp())
    s.add_app(StaticApp(prefix='static', path='static'))
    #create Sitelist
    s.serve()
