'''
1. MaxMeanSubArray
Given an array of integers and an integer, k, find the maximum mean of a subarray of size k.

sliding window technique
time taken: 30 minutes

'''
def MaxMeanSubArray(array, k):

    if (not array or k==0):
        return 0
    if (k > len(array)):
        return "The window is too big!"

    maxMean = 0
    minIndex = 0
    maxIndex = k

    while (maxIndex <= len(array)):
        sum = 0
        for i in range(minIndex, maxIndex):
            sum = sum + array[i]
        mean = sum/k
        if mean > maxMean:
            maxMean = mean

        minIndex = minIndex + 1
        maxIndex = maxIndex + 1


    return maxMean

#Test Cases:
arr1 = [4, 5, -3, 2, 6, 1]
arr2 = [1, 1, 1, 1, -1, -1, 2, -1, -1]
arr3 = [1, 1, 1, 1, -1, -1, 2, -1, -1, 6]

#The following 4 test cases come from the spec.
print (MaxMeanSubArray(arr1, 2)) #Output: 4.5
print (MaxMeanSubArray(arr1, 3)) # Output: 3.0
print (MaxMeanSubArray(arr2, 3)) #Output: 1
print (MaxMeanSubArray(arr3, 5)) # Output: 1

#Edge cases:
# if an empty array is passed
print (MaxMeanSubArray([], 2)) #Output: 0

# if k = 0 is passed
print (MaxMeanSubArray([1, 2, 3], 0)) #Output: 0

# if k exceeds the length of array
print (MaxMeanSubArray([1, 2, 3], 5)) #Output: "The window is too big!"
