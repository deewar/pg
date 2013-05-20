#!/bin/bash


for f in $( find ./games/ -type f  -iname "*.game" ) 
do 
    valgrind ./main $f | tee $f.result ;
done