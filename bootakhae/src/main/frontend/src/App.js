import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Container} from 'react-bootstrap';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import LoginForm from './pages/user/LoginForm.js'
import Main from './pages/Main'

const App = () => {
  return (
      <Router>
        <Routes>
          {/*<Switch>*/}
          <Route path="/login" element={<LoginForm />}/>
          <Route path="/main/:data" element={<Main />}/>
          {/*</Switch>*/}
        </ Routes>
        {/* Add more routes as needed */}
      </Router>
  )
      ;
};
// function App() {
//   const [hello, setHello] = useState('');

// useEffect(() => {
//   axios.get('/')
//   .then(response => setHello(response.data))
//   .catch(error => console.log(error))
// }, []);
// useEffect(() => {
//   fetch("/login")
//   .then((res)=>{
//     return res.json();
//   })
//   .then(function (result){
//     setHello(result);
//   })
// }, []);

//   return (
//       <div>
//         <Container>
//             <Router>
//               <Routes>
//                 {/*<Route path="/" element={<Main />}></Route>*/}
//                 <Route path="/sss" element={<LoginForm />}/>
//               </ Routes>
//             </ Router>
//         </Container>
//         백엔드에서 가져온 데이터입니다 : {hello}
//       </div>
//   );
// }

export default App;