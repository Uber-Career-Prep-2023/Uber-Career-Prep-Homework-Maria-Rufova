'''
1. MaxMeanSubArray
Given an array of integers and an integer, k, find the maximum mean of a subarray of size k.

Technique: Sliding Window
Time Complexity: O(n)
Space Complexity: O(n)
Time Taken: 30 minutes


'''
def MaxMeanSubArray(array, k):

    # check is array is empty, or k window length is 0,
    # or k window length is greater than length of array
    if (not array or k==0): 
        return 0
    if (k > len(array)):
        return "The window is too big!"

    maxMean = 0 #maximum mean found so far
    minIndex = 0 #first index of window
    maxIndex = k #last index of window

    # while the left edge of the window does not hit the end of the array
    while (maxIndex <= len(array)):

        #find the mean of current window
        sum = 0
        for i in range(minIndex, maxIndex):
            sum = sum + array[i]
        mean = sum/k

        #if current mean is greater than maxMean, update maxMean
        if mean > maxMean:
            maxMean = mean

        #move the window to the right
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
