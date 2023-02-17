import React from "react";
import {Box, Container, Divider, Grid, Link, Typography} from "@mui/material";
import logo from "../../../assets/images/logo_footer.png";
import Button from "@mui/material/Button";
import FacebookOutlinedIcon from '@mui/icons-material/FacebookOutlined';
import InstagramIcon from '@mui/icons-material/Instagram';
import GitHubIcon from '@mui/icons-material/GitHub';
import {useNavigate} from "react-router-dom";


const pages = [
    {label: 'Regulamin', href: "/regulamin"},
    {label: 'FAQ', href: "/faq"},
    {label: 'Dojazd', href: "/dojazd"},
    {label: 'Kontakt', href: "/kontakt"}
];

export default function Footer() {

    const navigate = useNavigate();

    return (
        <Box
            component="footer"
            sx={{
                py: 3,
                px: 2,
                mt: 'auto',
                backgroundColor: '#eeeeee',
            }}
        >
            <Container maxWidth="xl">
                <Grid container spacing={3} justifyContent="space-between" alignItems="center">
                    <Grid item xs={12} md={4}>
                        <Box sx={{display: "flex", justifyContent: {xs: "center", md: "flex-start"}}}>
                            <Box sx={{ display: "flex", mr: 1, width: 36, height: 36}}>
                                <img src={logo} alt="Logo"/>
                            </Box>
                            <Typography
                                variant="h6"
                                noWrap
                                component="a"
                                url="/"
                            >
                                MOSIR
                            </Typography>
                        </Box>
                    </Grid>
                    <Grid item xs={12} md={8} order={{xs: 3, md: 2}}>
                            <Box sx={{display: "flex", justifyContent: {xs: "center", md: "flex-end"}}}>
                                {pages.map((page) => (
                                    <Button
                                        key={page.label}
                                        sx={{ my: 2, color: 'black', display: 'block' }}
                                        onClick={() => navigate(page.href)}
                                    >
                                        {page.label}
                                    </Button>
                                ))}
                            </Box>
                    </Grid>
                    <Grid item xs={12} order={{xs: 2, md: 3}}>
                        <Divider />
                    </Grid>
                    <Grid item xs={12} md={6} order={{xs: 5, md: 4}}>
                        <Typography variant="subtitle2" sx={{color: "#808080", textAlign: {xs: "center", md: "left"}}} component="p">
                            &#169; 2023 MOSIR. Wszystkie prawa zastrzeżone.
                        </Typography>
                    </Grid>
                    <Grid item xs={12} md={6} order={{xs: 4, md: 5}}>
                        <Box sx={{display: "flex", justifyContent: {xs: "center", md: "flex-end"}}}>
                            <Link href="https://www.facebook.com" target="_blank" color="inherit">
                                <FacebookOutlinedIcon sx={{width: 36, height: 36}}/>
                            </Link>
                            <Link href="https://www.instagram.com" target="_blank" color="inherit">
                                <InstagramIcon sx={{width: 36, height: 36, ml: 2}}/>
                            </Link>
                            <Link href="https://github.com/kuna728/mosir" target="_blank" color="inherit">
                                <GitHubIcon sx={{width: 36, height: 36, ml: 2}}/>
                            </Link>
                        </Box>
                    </Grid>
                </Grid>
            </Container>
        </Box>
    )
}