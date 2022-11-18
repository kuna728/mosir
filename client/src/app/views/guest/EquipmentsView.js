import React from "react";
import useEquipments from "../../query/hooks/useEquipments";
import {
    Box,
    LinearProgress,
    Paper,
    Table, TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "@mui/material";
import ErrorView from "./ErrorView";
import {SERVER_ERROR} from "../../utils/constans";
import StyledTableCell from "../../components/StyledTableCell";
import StyledTableRow from "../../components/StyledTableRow";

export default function EquipmentsView() {

    const equipments = useEquipments();

    return equipments.isLoading ? <LinearProgress /> : equipments.isError ? <ErrorView status={SERVER_ERROR} /> : (
        <Box sx={{mt: {xs: 2, md: 7}, mb: 5}}>
            <Typography variant="h2" component="h1" gutterBottom>
                Sprzęt sportowy
            </Typography>
            <Typography variant="h5" component="h2" gutterBottom>
                Mosir to nie tylko trenerzy i sale do uprawiania sportów. Oferujemy również wynajem sprzętu sportowego na czas zajęć.
                Sprzęt nie jest dodatkowo płatny, można go wybrać przy rezerwacji zajęć.
            </Typography>
            <TableContainer sx={{mt: 3}}component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <StyledTableCell>Sport</StyledTableCell>
                            <StyledTableCell align="right">Nazwa</StyledTableCell>
                            <StyledTableCell align="right">Model</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {equipments.data.map(equipment => (
                            <StyledTableRow key={equipment.model} >
                                <StyledTableCell component="th" scope="row">
                                    {equipment.activityType}
                                </StyledTableCell>
                                <StyledTableCell align="right">
                                    {equipment.name}
                                </StyledTableCell>
                                <StyledTableCell align="right">
                                    {equipment.model}
                                </StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    )
}