#Search occurrence of pattern
grep "pattern" /path/to/file

# case-insensitive search
grep -i "pattern" /path/to/file

# search as a separate word
grep -w "pattern" /path/to/file

# search as a separate word and show line numbers
grep -n -w "pattern" /path/to/file

# show lines before and after the match
grep -B 2 -A 3 "pattern" /path/to/file

# show lines that don't match
grep -v "pattern" /path/to/file

# search recursively
grep -r "pattern"

# get the count of the lines of the result
grep -v -c "pattern" /path/to/file

# print the filenames of a match
grep -l -r "pattern" /dir