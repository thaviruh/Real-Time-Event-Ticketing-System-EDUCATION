import React, { useState } from "react";
import Form from "./components/Form";
import Logs from "./components/Logs";
import Footer from "./components/Footer";
import Header from "./components/Header";

function App() {
  const [totalTickets, setTotalTickets] = useState("");
  const [maxTickets, setMaxTickets] = useState("");
  const [count, setCount] = useState("");
  const [count2, setCount2] = useState("");
  const [logs, setLogs] = useState([]);

  const handleConfigure = () => {
    const configMessage = `Configured with Total Tickets: ${totalTickets}, Max Tickets: ${maxTickets}, Count: ${count}, Count 2: ${count2}`;
    setLogs((prevLogs) => [...prevLogs, configMessage]);
  };

  const handleStart = () => {
    setLogs((prevLogs) => [...prevLogs, "System started."]);
  };

  const handleStop = () => {
    setLogs((prevLogs) => [...prevLogs, "System stopped."]);
  };

  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <main className="flex flex-col items-center p-5 flex-grow">
        <div className="flex flex-col items-center border p-8 rounded-md bg-gray-100 w-1/4">
          <Form
            totalTickets={totalTickets}
            maxTickets={maxTickets}
            count={count}
            count2={count2}
            setTotalTickets={setTotalTickets}
            setMaxTickets={setMaxTickets}
            setCount={setCount}
            setCount2={setCount2}
            handleConfigure={handleConfigure}
          />
        </div>
        <div className="flex mt-5 space-x-3">
          <button
            onClick={handleStart}
            className="bg-green-500 text-white px-4 py-2 rounded"
          >
            Start
          </button>
          <button
            onClick={handleStop}
            className="bg-red-500 text-white px-4 py-2 rounded"
          >
            Stop
          </button>
        </div>
        <Logs logs={logs} />
      </main>
      <Footer />
    </div>
  );
}

export default App;