LATEX = latex

DVIPS = dvips

PDFFLAGS = -dCompatibilityLevel=1.4 -dPDFSETTINGS=/prepress  \
           -dCompressPages=true -dUseFlateCompression=true  \
           -dEmbedAllFonts=true -dSubsetFonts=true -dMaxSubsetPct=100

DIR = /home/downey/public_html/greent/thinkapjava

thinkapjava:	thinkapjava.tex
	#latex thinkapjava
	makeindex thinkapjava
	latex thinkapjava
	dvips -Ppdf -o thinkapjava.ps thinkapjava
	evince thinkapjava.ps

pdf:
	ps2pdf $(PDFFLAGS) thinkapjava.ps thinkapjava.pdf

DISTFILES = thinkapjava.pdf thinkapjava.tex.zip

distrib:
	rm -rf thinkapjava
	mkdir thinkapjava thinkapjava/figs
	cp thinkapjava.tex thinkapjava
	cp Makefile.dist thinkapjava/Makefile
	cp figs/*.fig thinkapjava/figs
	cp figs/*.eps thinkapjava/figs
	zip -r thinkapjava.tex.zip thinkapjava
	chmod 644 $(DISTFILES)
	cp $(DISTFILES) $(DIR)

distcode:
	cp code/IntList/code/IntList.java $(DIR)/code/IntList

distsol:
	cp -r solutions /home/downey/public_html/thinkapjava
	chmod -R o+r /home/downey/public_html/thinkapjava

clean:
	rm -f *~ *.aux *.log *.dvi *.idx *.ilg *.ind *.toc
