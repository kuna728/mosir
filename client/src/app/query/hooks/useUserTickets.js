import {useQuery} from "@tanstack/react-query";
import tokenFetcher from "../fetchers/tokenFetcher";

export default function useUserTickets(auth) {
    return useQuery({queryKey: ['user_tickets'], queryFn: () => tokenFetcher("/api/ticket", auth.token)});
}