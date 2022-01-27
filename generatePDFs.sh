#! /bin/bash

### Generating SVGs from dot files
# for i in *.dot ;do dot -Tsvg ${i%} > ./svg/${i%.*}.svg $i;done

### Converting SVGs to pdf
# rsvg-convert -f pdf -o pdfs_merged.pdf *.pdf

# javac ./src/Main.javac

# java ./bin/Main

# cd ../

rm ./pdf/*.pdf

rm *.dot

## only working on my own machine 

/usr/bin/env /Library/Java/JavaVirtualMachines/jdk-11.0.12.jdk/Contents/Home/bin/java -cp /Users/jonathanschlitt/Desktop/Java-RBT/bin Main

## Java commands for compiling and executing

# cd src

# javac *.java
# java Main

# cd ..

### Generating PDFs from dot files
for i in *.dot ;do dot -Tpdf ${i%} > ./pdf/${i%.*}.pdf $i;done

cd pdf 

### Stitching PDFs into one PDF
pdfunite *.pdf rbt_steps.pdf


