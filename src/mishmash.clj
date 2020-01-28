(ns mishmash)

(require '[clojure.string :as str])

(defn ! [x] (reduce * 1 (range 1 (inc x))))

(defn pascal [input]
  (try
    (let [
          level (Integer/parseInt input)
          ]
      (if (> level -1)
        (do
          (println (str/join " " (map #(/ (! level) (* (! %) (! (- level %)))) (range (+ level 1))))))
        (println "invalid input"))
      )
     (catch Exception e
       (do (println "invalid exc")))))



(defn write-roman [num])

(defn read-roman [numerals])

(defn -main [& args]
  (cond
    (not= (count args) 2) (do (println "invalid input"))
    (= (first args) "pascal") (pascal (nth args 1))
    (= (first args) "write-roman") (write-roman (rest args))
    (= (first args) "read-roman") (read-roman (rest args))
    :else (do (println "invalid input"))
    ))