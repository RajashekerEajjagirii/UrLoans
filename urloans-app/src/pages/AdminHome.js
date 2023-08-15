import React, { useState } from 'react';
import SideNav from '../components/SideNav';
import { Box, Stack, Typography } from '@mui/material';
import { Route, Routes,Link, Outlet } from 'react-router-dom';
import HomeloansList from './HomeloansList';
import PersonalLoansList from './PersonalLoansList';
import {List,ListItem,ListItemButton,ListItemIcon,ListItemText,Avatar} from '@mui/material';
import {FaHome} from "react-icons/fa";
import {LiaBusinessTimeSolid} from "react-icons/lia";
import {BsPersonBadge} from "react-icons/bs";
import {IoBusinessOutline} from "react-icons/io5";

const AdminHome = () => {
    
    
    return (
        <Stack sx={{marginTop:5}} direction={{xs:'column',lg:'row'}} >
            

            <Stack >
              <List className='side-bar-options'>
              
                <ListItem disablePadding sx={{ display: 'block' }} >
                    <ListItemButton LinkComponent={Link} to="/adminhome/homeloans"
                        sx={{
                    minHeight: 18,
                    justifyContent: 'center',
                    px: 0.5,
                        }}
                    >
                    <Avatar
                    sx={{
                        minWidth: 0,
                        mr: 'auto',
                        justifyContent: 'center',
                    }}
                    >
                     <FaHome style={{backgroundColor:'blue',fontSize:40}}/>
                    </Avatar>
                    <ListItemText primary="Home Loans" style={{marginLeft:8}}  />
                    </ListItemButton>
                </ListItem>

                <ListItem disablePadding sx={{ display: 'block' }}  >
                    <ListItemButton LinkComponent={Link} to="/adminhome/personalloans"
                        sx={{
                    minHeight: 18,
                    justifyContent: 'center',
                    px: 0.5,
                        }}
                    >
                    <Avatar
                    sx={{
                        minWidth: 0,
                        mr: 'auto',
                        justifyContent: 'center',
                    }}
                    >
                     <BsPersonBadge style={{backgroundColor:'blue',fontSize:35}}/>
                    </Avatar>
                <ListItemText primary="Personal Loans" style={{marginLeft:8}}  />
                </ListItemButton>
                </ListItem>
           
                <ListItem disablePadding sx={{ display: 'block' }}  >
                    <ListItemButton LinkComponent={Link} to="/adminhome/businessloans"
                        sx={{
                    minHeight: 18,
                    justifyContent: 'center',
                    px: 0.5,
                        }}
                    >
                    <Avatar
                    sx={{
                        minWidth: 0,
                        mr:'auto',
                        justifyContent: 'center',
                    }}
                    >
                     <LiaBusinessTimeSolid style={{backgroundColor:'blue',fontSize:35}}/>
                    </Avatar>
                    <ListItemText primary="Business Loans" style={{marginLeft:8}}  />
                    </ListItemButton>
                </ListItem>

                <ListItem disablePadding sx={{ display: 'block' }} >
                    <ListItemButton LinkComponent={Link} to="/adminhome/propertyloans"
                        sx={{
                    minHeight: 18,
                    justifyContent:'center',
                    px: 0.5,
                        }}
                    >
                    <Avatar
                    sx={{
                        minWidth: 0,
                        mr:'auto',
                        justifyContent: 'center',
                    }}
                    >
                     <IoBusinessOutline style={{backgroundColor:'blue',fontSize:40}}/>
                    </Avatar>
                    <ListItemText primary="Loan Against Property" style={{marginLeft:8}}  />
                    </ListItemButton>
                </ListItem>

              </List>
              
            </Stack>
            <Stack sx={{ml:{xs:2,lg:15}}}  mt={5}>
                <Outlet/>
            </Stack>

        </Stack>
    );
};

export default AdminHome;