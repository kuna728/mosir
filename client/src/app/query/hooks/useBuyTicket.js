import {useMutation, useQueryClient} from "@tanstack/react-query";
import tokenFetcher from "../fetchers/tokenFetcher";

export default function useBuyTicket (auth, ticket, discount) {

    const queryClient = useQueryClient();
    const ticketType = ticket.startsWith('s') ? 'SINGLE' : 'MULTI';
    const ticketId = parseInt(ticket.substring(2));

    return useMutation(() => tokenFetcher("/api/ticket", auth.token, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ticketType, ticketId, discountId: discount})
    }), {
        onSuccess: () => {
            queryClient.invalidateQueries(['user_tickets']);
        }
    })
}