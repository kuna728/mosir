import {useQuery} from "@tanstack/react-query";
import tokenFetcher from "../fetchers/tokenFetcher";

export default function useUserMultiTickets(page, auth) {
    return useQuery({
        queryKey: ['user_multi_tickets', page],
        queryFn: () => tokenFetcher(`/api/ticket/multi?page=${page}`, auth.token),
        keepPreviousData : true
    });
}