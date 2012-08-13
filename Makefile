LATEX = latex

DVIPS = dvips

PDFFLAGS = -dCompatibilityLevel=1.4 -dPDFSETTINGS=/prepress  \
           -dCompressPages=true -dUseFlateCompression=true  \
           -dEmbedAllFonts=true -dSubsetFonts=true -dMaxSubsetPct=100

DIR = /home/downey/public_html/greent/thinkapjava

thinkapjava:	thinkapjava.tex
	pdflatex thinkapjava
	makeindex thinkapjava.idx
	evince thinkapjava.pdf

hevea:
	sed 's/\(figs\/[^.]*\).pdf/\1.eps/' thinkapjava.tex > thinkjava.tex
	rm -rf html
	mkdir html
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

distrib:
	rm -rf thinkapjava
	mkdir thinkapjava thinkapjava/figs
	cp thinkapjava.tex latexonly htmlonly thinkapjava
	cp Makefile.dist thinkapjava/Makefile
	cp *.png thinkapjava
	cp figs/*.fig thinkapjava/figs
	cp figs/*.eps thinkapjava/figs
	cp figs/*.pdf thinkapjava/figs
	zip -r thinkapjava.tex.zip thinkapjava
	chmod 644 $(DISTFILES)
	cp $(DISTFILES) $(DIR)
	rsync -a html $(DIR)

distcode:
	cp code/IntList/code/IntList.java $(DIR)/code/IntList

distsol:
	cp -r solutions /home/downey/public_html/thinkapjava
	chmod -R o+r /home/downey/public_html/thinkapjava

clean:
	rm -f *~ *.aux *.log *.dvi *.idx *.ilg *.ind *.toc
