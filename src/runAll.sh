#!/bin/bash


for f in $( find ./games/ ) 
do 
    ./main $f | tee $f.result ;
done