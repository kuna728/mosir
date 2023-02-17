import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import logo from "../../../assets/images/logo_topbar.png"
import {Link, useNavigate} from "react-router-dom";
import {AuthContext} from "../../../auth/AuthContext";
import {ROLE_GUEST} from "../../../utils/constans";
import {AccountCircle} from "@mui/icons-material";

const pages = [
    {label: 'Sporty', href: "/sporty"},
    {label: 'Trenerzy', href: "/trenerzy"},
    {label: "Bilety", href: '/bilety'},
    {label: 'Sale', href: '/sale'},
    {label: 'Sprzęt sportowy', href: '/sprzet-sportowy'}
];

const user_pages = [
    {label: 'Mój mosir', href: "/moj-mosir"},
    {label: 'Zajęcia', href: "/zajecia"},
    {label: 'Bilety', href: "/moje-bilety"},
]

function TopBar() {

    const auth = React.useContext(AuthContext);
    const navigate = useNavigate();

    const [anchorElNav, setAnchorElNav] = React.useState(null);
    const [anchorElRight, setAnchorElRight] = React.useState(null);

    const handleOpenNavMenu = event => {
        setAnchorElNav(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleMenuButtonClick = href => {
        handleCloseNavMenu();
        handleCloseRightMenu();
        navigate(href);
    }

    const handleOpenRightMenu = event => {
        setAnchorElRight(event.currentTarget)
    }

    const handleCloseRightMenu = () => {
        setAnchorElRight(null);
    }

    const handleLogoutButtonClick = () => {
        auth.logout();
        setAnchorElRight(null);
        navigate("/");
    }

    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Box sx={{ display: { xs: 'none', md: 'flex' }, mr: 1, width: 36, height: 36}}>
                        <img src={logo} alt="Logo"/>
                    </Box>
                    <Typography
                        variant="h6"
                        noWrap
                        component="p"
                        sx={{
                            mr: 2,
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        <Link to="/">
                            MOSIR
                        </Link>
                    </Typography>
                    <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                        <IconButton
                            size="large"
                            onClick={handleOpenNavMenu}
                            color="inherit"
                        >
                            <MenuIcon />
                        </IconButton>
                        <Menu
                            id="left-menu"
                            anchorEl={anchorElNav}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(anchorElNav)}
                            onClose={handleCloseNavMenu}
                            sx={{
                                display: { xs: 'block', md: 'none' },
                            }}
                        >
                            {pages.map((page) => (
                                <MenuItem key={page.label} onClick={() => handleMenuButtonClick(page.href)}>
                                    <Typography textAlign="center">{page.label}</Typography>
                                </MenuItem>
                            ))}
                        </Menu>
                    </Box>
                    <Box sx={{ display: { xs: 'flex', md: 'none' }, mr: 1, width: 36, height: 36}}>
                        <img src={logo} alt="Logo"/>
                    </Box>
                    <Typography
                        variant="h5"
                        noWrap
                        component="p"
                        sx={{
                            mr: 2,
                            display: { xs: 'flex', md: 'none' },
                            flexGrow: 1,
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        <Link to="/">
                            MOSIR
                        </Link>
                    </Typography>
                    <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                        {pages.map((page) => (
                            <Button
                                key={page.label}
                                onClick={() => handleMenuButtonClick(page.href)}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                            >
                                {page.label}
                            </Button>
                        ))}
                    </Box>

                    <Box sx={{ flexGrow: 0}}>
                            <Button
                                sx={{ my: 2, color: 'white',display: auth.role === ROLE_GUEST ? "block" : "none" }}
                                onClick={() => navigate("/logowanie")}
                            >
                                Logowanie
                            </Button>
                        <Box sx={{display: auth.role !== ROLE_GUEST ? "block" : "none"}}>
                            <IconButton
                                size="large"
                                onClick={handleOpenRightMenu}
                                color="inherit"
                            >
                            <AccountCircle />
                            </IconButton>
                            <Menu
                                id="right-menu"
                                anchorEl={anchorElRight}
                                anchorOrigin={{
                                    vertical: 'bottom',
                                    horizontal: 'right',
                                }}
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                open={Boolean(anchorElRight)}
                                onClose={handleCloseRightMenu}
                                display={auth.role !== ROLE_GUEST ? "block" : "none"}
                            >
                                <MenuItem sx={{borderBottom: "1px solid black"}} onClick={() => navigate('/moje-konto')}>
                                    <Typography textAlign="center" fontWeight="bold">{auth.legalName}</Typography>
                                </MenuItem>
                                {user_pages.map((page) => (
                                    <MenuItem key={page.label} onClick={() => handleMenuButtonClick(page.href)}>
                                        <Typography textAlign="center">{page.label}</Typography>
                                    </MenuItem>
                                ))}
                                <MenuItem onClick={handleLogoutButtonClick}>
                                    <Typography textAlign="center">Wyloguj</Typography>
                                </MenuItem>
                            </Menu>
                        </Box>
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}
export default TopBar;