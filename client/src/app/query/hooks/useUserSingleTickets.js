import {useQuery} from "@tanstack/react-query";
import tokenFetcher from "../fetchers/tokenFetcher";

export default function useUserSingleTickets(page, auth) {
    return useQuery({
        queryKey: ['user_single_tickets', page],
        queryFn: () => tokenFetcher(`/api/ticket/single?page=${page}`, auth.token),
        keepPreviousData : true
    });
}