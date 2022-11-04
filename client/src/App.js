import {BrowserRouter, Route, Routes} from "react-router-dom";
import BaseTemplate from "./app/templates/BaseTemplate/BaseTemplate";
import HomeView from "./app/views/HomeView";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<BaseTemplate />}>
                <Route path="/" exact element={<HomeView />} />
            </Route>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
