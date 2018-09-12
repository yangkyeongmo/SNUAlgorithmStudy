fp <- file('stdin', 'r')
a <- scan(fp, what=integer(), n=1)
b <- scan(fp, what=integer(), n=1)
cat(a - b)
