thinkapjava:	thinkapjava.tex
	pdflatex thinkapjava
	makeindex thinkapjava.idx
	pdflatex thinkapjava

hevea:
	sed 's/\(figs\/[^.]*\).pdf/\1.eps/' thinkapjava.tex > thinkjava.tex
	rm -rf html
	mkdir html
	hevea -O -e latexonly htmlonly thinkjava
	hevea -O -e latexonly htmlonly thinkjava
	imagen -png thinkjava
	hacha thinkjava.html
	cp up.png next.png back.png html
	mv index.html thinkjava.css thinkjava*.html thinkjava*.png *motif.gif html

plastex:
	rm -rf /home/downey/thinkapjava/trunk/xml/.svn
	cp thinkapjava.tex xml.tex
	plastex --renderer=DocBook --theme=book --image-resolution=300 --filename=thinkapjava.xml xml.tex
	rm -rf /home/downey/thinkapjava/trunk/xml/.svn

xxe:
	~/Downloads/xxe-perso-4_8_0/bin/xxe xml/thinkapjava.xml

epub:
	cd html; ebook-convert thinkapjava.html thinkpython.epub

DISTFILES = thinkapjava.pdf thinkapjava.tex.zip
DIR = /home/downey/public_html/greent/thinkapjava

distrib:
	rm -rf thinkapjava
	mkdir thinkapjava thinkapjava/figs
	cp thinkapjava.tex latexonly htmlonly thinkapjava
	cp Makefile thinkapjava/Makefile
	cp *.png *.html thinkapjava
	cp figs/* thinkapjava/figs
	zip -r thinkapjava.tex.zip thinkapjava
	chmod 644 $(DISTFILES)
	cp $(DISTFILES) $(DIR)
	rsync -a html $(DIR)

clean:
	rm -f *~ *.aux *.dvi *.idx *.ilg *.ind *.log *.out *.toc thinkjava.*
