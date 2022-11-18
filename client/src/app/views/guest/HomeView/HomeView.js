import React from "react";
import {Box, Typography} from "@mui/material";
import ImageGallery from "./components/ImageGallery";


export default function HomeView() {
    return (
        <Box>
            <Box sx={{mt: {xs: 2, md: 7}}}>
                <Typography variant="h2" component="h1" gutterBottom>
                    Sport i rekreacja
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom>
                    To nasze motto. Występuję także w naszej nazwie. Jesteśmy miejskim ośrodkiem sportu i rekreacji.
                </Typography>
                <Typography variant="body1">
                    Przygotowaliśmy dla Ciebie ofertę sportową w wielu różnych dyscyplinach. Nasz obiekt posiada wiele
                    dobrze wyposażonych sal na których uprawianie ulubionej dyscypliny będzie czystą przyjemnością. Istnieję
                    możliwość wypożyczenia większości powszechnie używanych sprzętów sportowych na miejscu. Nad Twoimi ćwiczeniami
                    i twoim bezpieczeństwem czuwa doskonale wyszkolona kadra trenerska. Aby wejść na obiekt i w pełni korzystać
                    z aplikacji wymagane jest konto w systemie. Z poziomu konta można kupować jednorazowe wejściowki oraz korzystniejsze
                    cenowo karnety.
                </Typography>
            </Box>
            <Box sx={{mt: 7}}>
                <Typography variant="h2" component="h1" gutterBottom>
                    Galeria
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom sx={{mb: 2}}>
                    Przejrzyj galerię zdjęć i zobacz na własne oczy, że warto dołączyć do mosiru.
                </Typography>
                <ImageGallery />
            </Box>
        </Box>


    )
}