(ns mishmash)

(require '[clojure.string :as str])

(def numeral-chart {:I 1
                    :V 5
                    :X 10
                    :L 50
                    :C 100
                    :D 500
                    :M 1000
                    :CM 900
                    :XC 90
                    :IX 9
                    :IV 4
                    :XL 40
                    :CD 400
                    :O 0})

(defn pascal [input]
  (try
    (let [
          level (Integer/parseInt input)
          ! (fn [x] (reduce * 1 (range 1 (inc x))))
          ]
      (if (> level -1)
        (do
          (println (str/join " " (map #(/ (! level) (* (! %) (! (- level %)))) (range (+ level 1))))))
        (println "invalid input"))
      )
     (catch Exception e
       (do (println "invalid input")))))

(defn num-to-roman [[total string] tuple]
  (let [char-val (val tuple)]
    (if (and (>= total char-val) (> total 0))
      [(- total (* (Math/floor (/ total char-val)) char-val))
       (apply str string (repeat (Math/floor (/ total char-val)) (name (key tuple))))]
      [total string]))

  )

(defn write-roman [num]
  (try
    (let [
          num (re-matches #"[0-9]+" (first num))
          int-num (Integer/parseInt num)
          comparator (into
                       (sorted-map-by
                         (fn [key1 key2]
                           (compare (get numeral-chart key2) (get numeral-chart key1)))) numeral-chart)
          ]
      (if (< int-num 4000)
        (println (second (reduce num-to-roman [int-num ""] comparator)))
        (println "invalid input"))
      )
    (catch Exception e (println "invalid input")))
  )

(defn numeral-reader [[total past-char] char]
  (try
    (let [
          past-val (get numeral-chart (keyword past-char))
          char-val (get numeral-chart (keyword char))
          ]
      (if (< past-val char-val)
        [(+ total (* -2 past-val) char-val) char]
        [(+ total char-val) char])
      )
    (catch Exception e
      (println "invalid input")))
  )

(defn read-roman [numerals]
  (try
    (let [
          numerals (re-matches #"[IVXLMCD]+" (first numerals))
          ]
      (if (some? numerals)
        (println (first (reduce numeral-reader [0, "O"] (str/split numerals #""))))
        (println "invalid input")
        )

      )
    (catch Exception e "invalid input"))
  )



(defn -main [& args]
  (cond
    (not= (count args) 2) (do (println "invalid input"))
    (= (first args) "pascal") (pascal (nth args 1))
    (= (first args) "write-roman") (write-roman (rest args))
    (= (first args) "read-roman") (read-roman (rest args))
    :else (println "invalid input"))
    )
