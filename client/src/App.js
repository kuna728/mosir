import {BrowserRouter, Route, Routes} from "react-router-dom";
import BaseTemplate from "./app/templates/BaseTemplate/BaseTemplate";
import HomeView from "./app/views/guest/HomeView/HomeView";
import ErrorView from "./app/views/guest/ErrorView";
import ScrollToTop from "./app/utils/ScrollToTop";
import React from "react";
import {NOT_FOUND, NOT_IMPLEMENTED} from "./app/utils/constans";
import ActivitiesView from "./app/views/guest/ActivitiesView";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import CoachesView from "./app/views/guest/CoachesView/CoachesView";

function App() {

  const queryClient = new QueryClient();

  return (
      <QueryClientProvider client={queryClient} >
        <BrowserRouter>
            <ScrollToTop />
            <Routes>
                <Route path="/" element={<BaseTemplate />}>
                    <Route path="/" exact element={<HomeView />} />
                    <Route path="/regulamin" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="/faq" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="/dojazd" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="/kontakt" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="/sporty" exact element={<ActivitiesView />} />
                    <Route path="/trenerzy" exact element={<CoachesView />} />
                    <Route path="/bilety" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="/sale" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="/sprzet-sportowy" exact element={<ErrorView status={NOT_IMPLEMENTED} />} />
                    <Route path="*" element={<ErrorView status={NOT_FOUND}/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
      </QueryClientProvider>
  );
}

export default App;
