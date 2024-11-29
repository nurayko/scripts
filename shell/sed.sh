#replace all matches in place in file
sed -i 's|pattern||' file

#replace all matches in place in file case-insensitive
sed -i 's|pattern||I' file

#append to the end of the file
sed -i '$ a Text to append' file

#insert at beginning
sed -i '1 i Text to insert' file

# delete lines (10-30)
sed -i '10,30d' file

# read lines from file
sed -n '50,60p' file