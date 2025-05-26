function twoSum(nums: number[], target: number): number[] {
  const seen: Record<number, number> = {};

  for (let i = 0; i < nums.length; i++) {
    const diff = target - nums[i];

    if (seen[diff] !== undefined) {
      return [seen[diff], i];
    }

    seen[nums[i]] = i;
  }

  return [];
}

const nums = [2, 7, 11, 15];
const target = 9;

console.log(twoSum(nums, target));

