import {useQuery} from "@tanstack/react-query";
import baseFetcher from "../fetchers/baseFetcher";

export default function useHalls() {
    return useQuery({queryKey: ['halls'], queryFn: () => baseFetcher("/api/home/halls")});
}