import {useQuery} from "@tanstack/react-query";
import baseFetcher from "../fetchers/baseFetcher";

export default function useTickets() {
    return useQuery({queryKey: ['tickets'], queryFn: () => baseFetcher("/api/home/tickets")});
}