import {BrowserRouter, Route, Routes} from "react-router-dom";
import BaseTemplate from "./app/templates/BaseTemplate/BaseTemplate";
import HomeView from "./app/views/guest/HomeView/HomeView";
import ErrorView from "./app/views/guest/ErrorView";
import ScrollToTop from "./app/utils/ScrollToTop";
import React from "react";
import {NOT_FOUND, NOT_IMPLEMENTED, ROLE_USER} from "./app/utils/constans";
import ActivitiesView from "./app/views/guest/ActivitiesView";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import CoachesView from "./app/views/guest/CoachesView/CoachesView";
import TicketsView from "./app/views/guest/TicketsView/TicketsView";
import HallsView from "./app/views/guest/HallsView";
import EquipmentsView from "./app/views/guest/EquipmentsView";
import LoginView from "./app/views/guest/LoginView";
import BuyTicketView from "./app/views/user/BuyTicketView/BuyTicketView";
import PrivateRoute from "./app/auth/PrivateRoute";
import AuthProvider from "./app/auth/AuthProvider";
import UserTicketsView from "./app/views/user/UserTicketsView/UserTicketsView";
import NewAccountView from "./app/views/guest/NewAccountView";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment';
import ResetPasswordView from "./app/views/guest/ResetPasswordView";
import ChangePasswordView from "./app/views/guest/ChangePasswordView";
import ActivateAccountView from "./app/views/guest/ActivateAccountView";

function App() {

  const queryClient = new QueryClient();

  return (
      <LocalizationProvider dateAdapter={AdapterMoment}>
      <QueryClientProvider client={queryClient} >
        <BrowserRouter>
            <ScrollToTop />
            <AuthProvider>
                <Routes>
                    <Route path="/" element={<BaseTemplate />}>
                        {/*GUEST VIEWS*/}
                        <Route path="/" exact element={<HomeView />} />
                        <Route path="/regulamin" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                        <Route path="/faq" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                        <Route path="/dojazd" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                        <Route path="/kontakt" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                        <Route path="/sporty" exact element={<ActivitiesView />} />
                        <Route path="/trenerzy" exact element={<CoachesView />} />
                        <Route path="/bilety" exact element={<TicketsView />} />
                        <Route path="/sale" exact element={<HallsView />} />
                        <Route path="/sprzet-sportowy" exact element={<EquipmentsView />} />
                        <Route path="/logowanie" exact element={<LoginView />} />
                        <Route path="/rejestracja" exact element={<NewAccountView />} />
                        <Route path="/aktywuj-konto" exact element={<ActivateAccountView />} />
                        <Route path="/resetuj-haslo" exact element={<ResetPasswordView />} />
                        <Route path="/zmien-haslo" exact element={<ChangePasswordView />} />

                        {/*USER VIEWS*/}
                        <Route element={<PrivateRoute accessLevel={ROLE_USER} /> } >
                            <Route path="/moje-konto" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                            <Route path="/moj-mosir" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                            <Route path="/zajecia" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                            <Route path="/kup-bilet" exact element={<BuyTicketView />} />
                            <Route path="/moje-bilety" exact element={<UserTicketsView />} />
                        </Route>

                        <Route path="*" element={<ErrorView status={NOT_FOUND}/>}/>
                    </Route>
                </Routes>
            </AuthProvider>
        </BrowserRouter>
      </QueryClientProvider>
      </LocalizationProvider>
  );
}

export default App;
