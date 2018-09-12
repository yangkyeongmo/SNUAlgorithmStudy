fp <- file('stdin', 'r')

n_arr <- scan(fp, what=integer(), n=1)

arr <- integer(0)
for (i in 1:n_arr) {
  new_int <- scan(fp, what=integer(), n=1)
  
  if (new_int %in% arr)
    next
  
  else
    arr <- c(arr, new_int)
}

arr_sorted <- sort(arr)
cat(arr_sorted, sep='\n')
