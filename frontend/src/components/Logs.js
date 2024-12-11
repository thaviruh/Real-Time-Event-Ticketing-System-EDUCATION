import React from "react";

function Logs({ logs }) {
  return (
    <div className="mt-5 w-96">
      <h3 className="font-bold mb-2">Logs</h3>
      <div className="border border-gray-300 h-48 overflow-y-scroll p-2 bg-gray-50 rounded">
        {logs.map((log, index) => (
          <div key={index} className="text-sm">
            {log}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Logs;
