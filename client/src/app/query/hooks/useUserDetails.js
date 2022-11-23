import {useQuery} from "@tanstack/react-query";
import tokenFetcher from "../fetchers/tokenFetcher";

export default function useUserDetails(auth) {
    return useQuery({queryKey: ['user_details'], queryFn: () => tokenFetcher("/api/user", auth.token)});
}