import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import axios from "axios";

export const MEMBER_ENTITY_ID_SESSION = 'memberId'
export const MEMBER_ENTITY_USER_ID_SESSION = 'userId'

const Header = () => {
  const [loginId, setLoginId] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setLoggedIn] = useState(false);

  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post('/login', {
        loginId,
        password,
      });

      if (response.status === 200) {
        console.log('Login successful');
        sessionStorage.setItem(MEMBER_ENTITY_ID_SESSION, response.data.id);
        sessionStorage.setItem(MEMBER_ENTITY_USER_ID_SESSION, response.data.userId);
        setLoggedIn(true);
        navigate('/main');
      }
    } catch (error) {
      console.error('Login failed', error);
    }
  };

  const onLogout = async () => {
    try {
      const response = await axios.get('/logout');
      if (response.status === 200) {
        console.log('Login successful');
        sessionStorage.removeItem(MEMBER_ENTITY_ID_SESSION);
        sessionStorage.removeItem(MEMBER_ENTITY_USER_ID_SESSION);
        setLoggedIn(false);
        navigate('/main');
      }
    } catch (error) {
      console.error('Login failed', error);

    }
  }
  return (
      <header>
        {/*<link rel="stylesheet" href="../css/styles.css"/>*/}
        <div>
          &nbsp;&nbsp;|&nbsp;&nbsp;
          <Link to="/">홈</Link>
          &nbsp;&nbsp;|&nbsp;&nbsp;
          <Link to="/boardList">게시판</Link>
          &nbsp;&nbsp;|&nbsp;&nbsp;
          {isLoggedIn ? (
              <span>
                Welcome, User {loginId}!
                <button onClick={onLogout}>Logout</button>
              </span>
          ) : (
              <span>
                <label>Username:</label>
                <input type="text"
                       onChange={(e) => setLoginId(e.target.value)}/>

                <label>Password:</label>
                <input type="password"
                       onChange={(e) => setPassword(e.target.value)}/>

                <button onClick={handleLogin}>Login</button>
              </span>)}
          <hr/>
        </div>
      </header>
  )
};

export default Header;