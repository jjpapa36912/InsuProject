import React, {useState} from "react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const LoginForm = (props) => {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post('/login', {
        loginId,
        password,
      });

      if (response.status === 200) {
        console.log('Login successful');
        navigate('/main/'+loginId);
      }
    } catch (error) {
      console.error('Login failed', error);
    }
  };

  return (
      <div>
        <label>Username:</label>
        <input type="text" value={loginId} onChange={(e) => setLoginId(e.target.value)} />

        <label>Password:</label>
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

        <button onClick={handleLogin}>Login</button>
      </div>
  );

}
//   const [inputId, setInputId] = useState("");
//   const [inputPw, setInputPw] = useState("");
//
//   const handleInputId = (e) => {
//     setInputId(e.target.value);
//   };
//
//   const handleInputPw = (e) => {
//     setInputPw(e.target.value);
//   };
//   return <div>
//           <input type="email" className="form-control" placeholder="Enter email"
//                  name="input_id" value={inputId} onChange={handleInputId}/>
//
//           <input type="password" className="form-control" placeholder="Enter password"
//                  name="input_pw" value={inputPw} onChange={handleInputPw}/>
//         </div>
// }


export default LoginForm;

