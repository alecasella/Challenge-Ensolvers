import React, { useContext, useState } from "react";
import Axios from 'axios'
import {
    Row
} from 'reactstrap';
import { useNavigate } from "react-router-dom";
import { AuthContext } from '../../contexts/auth';


const Login = () => {
  const { login } = useContext(AuthContext);

  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const isAValidCase = () => {
    if (!email.trim() && !password.trim()) setError('Params cant be null');
    if (!password.trim()) setError('Password cant be null');
    else return email !== '' && password !== '';
  }

  const registUser = (e) => {
    e.preventDefault();

    const validCase = isAValidCase();

    if (validCase) {

      const user = {
        email: email,
        password: password
      }

      try {
        Axios.post("http://localhost:8080/api/auth/register", user).then((resp => {
          if (resp.data.trim) setError(resp.data)
          else alert("Usuario creado con exito");
        }))
      } catch (e) { console.log(e); }

      setEmail('');
      setPassword('');
    }
  }

  const logUser = (e) => {
    e.preventDefault();

    const logUser = {
      email: email,
      password: password
    }

    try {
      Axios.post("http://localhost:8080/api/auth/login", logUser).then((resp => {
        if (resp.data.trim) setError(resp.data);
        else {
          const user = {
            id: resp.data.id,
            email: resp.data.email,
          }

          login(user);

          navigate(`/notes`);
        }
      }))
    } catch (e) { console.log(e); }

  }


  return (
    <Row>
      <h1 className="d-flex justify-content-center mt-5">Login Form</h1>
      <div className="d-flex justify-content-center">
        <form onSubmit={registUser} className="form-group">
          <div className="d-flex">
            <label className="mt-2 text-center w-50">Email:</label>
            <input className="form-control text-center" placeholder="Email" type="email" onChange={(e) => setEmail(e.target.value)} value={email} />
          </div>
          <div className="d-flex  ">
            <label className="mt-3  w-50"> Password:</label>
            <input className="form-control mt-2 text-center" placeholder="Password" type="password" onChange={(e) => setPassword(e.target.value)} value={password} />
          </div>

          <input className="form-control btn btn-success btn-block mt-1" type="submit" value="Register" />

          <button className="form-control btn btn-primary btn-block mt-1" type="submit" onClick={logUser}>Log in</button>
          {
            error ?
              (
                <ul className="list-group">
                  <li className="list-group-item mt-2 bg-light">{error}</li>
                </ul>
              )
              :
              (
                <span />
              )
          }
        </form>
      </div>

    </Row>
  );
};

export default Login;
