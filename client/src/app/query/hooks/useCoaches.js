import {useQuery} from "@tanstack/react-query";
import baseFetcher from "../fetchers/baseFetcher";

export default function useCoaches() {
    return useQuery({queryKey: ['coaches'], queryFn: () => baseFetcher("/api/home/coaches")});
}