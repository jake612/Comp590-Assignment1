(ns mishmash)

(require '[clojure.string :as str])

(defn ! [x] (reduce * 1 (range 1 (inc x))))

(defn pascal [level]
  (let [level (Integer/parseInt level)]
    (if (> level -1)
      (do
        (print (str/join " " (map #(/ (! level) (* (! %) (! (- level %)))) (range (+ level 1))))))
      (println "invalid input"))
    )
  )


(defn write-roman [num])

(defn read-roman [numerals])

(defn -main [& args]
  (cond
    (= 0 (count args)) (do (print "invalid input"))
    (= (first args) "pascal") (pascal (nth args 1))
    (= (first args) "write-roman") (write-roman (rest args))
    (= (first args) "read-roman") (read-roman (rest args))
    :else (do (print "invalid input"))
    ))