#! /bin/bash

### Generating SVGs from dot files
# for i in *.dot ;do dot -Tsvg ${i%} > ./svg/${i%.*}.svg $i;done

### Converting SVGs to pdf
# rsvg-convert -f pdf -o pdfs_merged.pdf *.pdf

# javac ./src/Main.javac

# java ./bin/Main

# cd ../

cd /Users/jonathanschlitt/Desktop/Testat_2/Testat2 ; /usr/bin/env /Library/Java/JavaVirtualMachines/jdk-11.0.12.jdk/Contents/Home/bin/java -cp /Users/jonathanschlitt/Desktop/Testat_2/Testat2/bin Main 

### Generating PDFs from dot files
for i in *.dot ;do dot -Tpdf ${i%} > ./pdf/${i%.*}.pdf $i;done

cd pdf 

### Stitching PDFs into one PDF
pdfunite *.pdf rbt_steps.pdf


