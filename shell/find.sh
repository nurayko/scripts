# find text files
find -type f -name *.txt

# find target-dir in a specified path
find /absolute/path -type d -name target-dir

# find directory with permissions
find -type d -perm 777

# find empty directories
find -type d -empty

# find hidden files
find -type f -name '.*'

# find files that belong to a user
find /path -user apache

# find files that belong to a group
find /path -group apache

# find accessed files in the last hour
find /var/log -type f -amin -60

# find changed files in the last hour
find /var/log -type f -cmin -60

# find files bigger than 1024MB
find -type f -size +1024M