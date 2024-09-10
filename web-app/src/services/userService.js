import httpClient from "../configurations/httpClient";
import { API } from "../configurations/configuration";
import { getToken } from "./localStorageService";

export const getMyInfo = async () => {
  return await httpClient.get(API.MY_INFO, {
    headers: {
      Authorization: `Bearer ${getToken()}`,
    },
  });
};

export const getProfile = async (userId) => {
  let url = API.PROFILE + "/9a62163a-5574-43da-ae8b-5c53993739ff";
  return await httpClient.get(url, {
    headers: {
      Authorization: `Bearer ${getToken()}`,
    },
  });
};
