import {useQuery} from "@tanstack/react-query";
import baseFetcher from "../fetchers/baseFetcher";

export default function useMembershipCards() {
    return useQuery({queryKey: ['membershipCards'], queryFn: () => baseFetcher("/api/home/membership-cards")});
}