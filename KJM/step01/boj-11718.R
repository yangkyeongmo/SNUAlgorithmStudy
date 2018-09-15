fp <- file('stdin', 'r')

str <- readLines(fp)

cat(str, sep='\n')
