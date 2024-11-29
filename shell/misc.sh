# wc
# get number of lines
wc -l filename

# nl
# nl to format the output
nl filename

# number empty lines as well
nl -b a filename

# change increment number
nl -i 5 -b a filename

# add "." to numbering
nl -s "." -b a filename

# tr
# change file letters from lowercase to uppercase
cat filename | tr [:lower:] [:upper:]

# remove blank lines
cat filename | -s \n
cat filename | -d \n

# sort
# sort by first column
sort filename

# sort reversed
sort -r filename

# sort by first column and then second column
sort -k 1,2 filename

# sort by numeric values ascending
sort -n filename

# sort by numeric values descending
sort -n -r filename

# provide column delimiter ","
sort -t , filename

# sort and print unique values
sort -u filename

# uniq
# uniq works when repeats are adjacent !

# print only unique ocurrences
sort filename | uniq -u

# print only repetead ocurrences
sort filename | uniq -d

# cut

# get first column with delimiter ","
cut -f 1 -d , filename

# get second and third columns
cut -f 2,3 -d , filename

# paste
# merge lines of 2 files
paste file1 file2

#provide delimiter ","
paste -d , file1 file2