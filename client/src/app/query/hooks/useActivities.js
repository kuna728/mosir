import {useQuery} from "@tanstack/react-query";
import baseFetcher from "../fetchers/baseFetcher";

export default function useActivities() {
    return useQuery({queryKey: ['activities'], queryFn: () => baseFetcher("/api/home/activity-types")});
}