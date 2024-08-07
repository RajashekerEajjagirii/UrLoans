import { Box } from "@mui/material";
import "./App.css";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import { Route, Routes, useNavigate } from "react-router-dom";
import Contact from "./pages/Contact";
import About from "./pages/About";
import Login from "./pages/Login";
import HomeLoan from "./pages/HomeLoan";
import PersonalLoan from "./pages/PersonalLoan";
import BusinessLoan from "./pages/BusinessLoan";
import PropertyLoans from "./pages/PropertyLoans";
import AdminHome from "./pages/AdminHome";
import HomeloansList from "./pages/HomeloansList";
import PersonalLoansList from "./pages/PersonalLoansList";
import SideNav from "./components/SideNav";
import { AuthProvider } from "./utils/useAuthen";
import { useAuthen } from "./utils/useAuthen";
import { useEffect } from "react";
import Appbar from "./components/Appbar";
import EditHomeLoans from "./pages/EditHomeLoans";
import HlUserView from "./pages/HlUserView";
import BusinessLoansList from "./pages/BusinessLoansList";
import LAPList from "./pages/LAPList";
import NotFound from "./pages/NotFound";

const App=()=> {
  
  // const { adminInfo, logout } = useAuthen();
  const navigate=useNavigate();
 
  useEffect(()=>{
    let admin=sessionStorage.getItem("dt");
    if(admin==='' || admin===null){
      navigate("/");
    }else{
      navigate("/adminhome/*");
    }
  },[])

  return (
    <AuthProvider>
      
        {/* <Navbar /> */}
        <Appbar/>

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/login" element={<Login />} />
          <Route path="/homeloans" element={<HomeLoan />} />            
          <Route path="/personalloans" element={<PersonalLoan />} />
          <Route path="/businessloans" element={<BusinessLoan />} />
          <Route path="/propertyloans" element={<PropertyLoans />} />
          {/* Admin Related Routes */}
          <Route path="/adminhome" element={<AdminHome />}>            
            <Route path="homeloans" element={<HomeloansList/>} />
            <Route path="homeloans/view/:id" element={<HlUserView/>} />
            <Route path="homeloans/edit/:id" element={<EditHomeLoans/>} />
            <Route path="personalloans" element={<PersonalLoansList />} />
            <Route path="businessloans" element={<BusinessLoansList />} />
            <Route path="propertyloans" element={<LAPList />} />
          </Route>
          <Route path="/*" element={<NotFound/>} />
          
        </Routes>
        <Footer />
      
    </AuthProvider>
  );
}

export default App;
