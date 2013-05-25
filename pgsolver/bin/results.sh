#! /bin/bash



#recursiveLadder

for g in clique  elevator    jurdzinski  ladder  modelCheckerLadder      towersOfHanoi
do 

    for f in $( find ../../games/$g -iname "*.game" ) 
    do
	echo $f
#rm -i $f.result
	./pgsolver --printsolonly -global smallprog $f | tee  $f.result
    done
done