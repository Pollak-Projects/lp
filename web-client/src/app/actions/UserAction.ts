"use server";
import logger from "@logger";
import axiosFetch from "@/src/lib/AxiosFetch";

const log = logger("user:UserActions");

export async function getUserById(userId: string) {
  log.debug("getUserById", userId);
  const response = await (await axiosFetch())?.get(`/api/v1/administration/user?id=${userId}`)!;

  if (![200, 204].includes(response.status)) {
    log.error("getUserById", response.data);
    throw new Error("Failed to get fetch user by id");
  }

  log.debug("getUserById", response.data);

  return response.data;
}