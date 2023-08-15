import React from 'react';
import { Box,Typography } from '@mui/material';
import HomeImg from "../assets/images/UrLoansImg.png"

const HomeBanner = () => {
    return (
        <Box sx={{mt:{lg:'130px', xs:'90px'},ml:{xs:'50px'}}}>
            <Typography fontWeight='600' fontSize='20px' 
              color='#42adf5'>
                URLOANS Club
            </Typography>
            <Typography fontWeight={700} sx={{fontSize:'30px',mt:{lg:'70px',xs:'50px'}}}  mb='23px'>
                Use URLOANS,<br/> To Achieve Ur Goals...
            </Typography>

            {/* <img src={HomeImg} alt='Home-image' className='home-banner-image'/> */}
            <Typography fontWeight={600} color='#42adf5' sx={{opacity:0.2,display:'block'}} fontSize='130px'>
                URLOANS
            </Typography>
        </Box>
    );
};

export default HomeBanner;