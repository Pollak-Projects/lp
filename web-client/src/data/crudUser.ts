import { useQueries, useQuery } from "@tanstack/react-query";
import { getUserById } from "@/src/app/actions/UserAction";

export function useGetUserById({ userId }: { userId: string }) {
  return useQuery({
    queryKey: ["getUserById", userId],
    queryFn: async () => getUserById(userId),
  });
}

export function useGetUsersByIds({ userIds }: { userIds: string[] }) {
  return useQueries({
    queries: userIds.map((userId) => ({
      queryKey: ["getUserById", userId],
      queryFn: async () => getUserById(userId),
    })),
  });
}
