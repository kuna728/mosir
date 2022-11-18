import {useQuery} from "@tanstack/react-query";
import baseFetcher from "../fetchers/baseFetcher";

export default function useEquipments() {
    return useQuery({queryKey: ['equipments'], queryFn: () => baseFetcher("/api/home/sports-equipments")});
}