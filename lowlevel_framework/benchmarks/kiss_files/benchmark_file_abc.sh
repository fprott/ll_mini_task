#!/bin/bash

FILENAME=$1

if [ ! -f $FILENAME ]
then
	echo "file not found"
	exit -1
fi

echo ${FILENAME%.blif}
../abc/abc -c "read_lut ../abc/6-lut-lib; read_blif $FILENAME; fpga -v; print_stats" |grep -vi "skip" > ${FILENAME%.blif}.bm

exit 0
