;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_5) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define manhattan-3d  ; val: non-negative integer number
  (lambda (x y z)     ; same as val, they represent the distance between starting and ending point in the three spacial vectors
    ; base cases
    (cond 
        ; if starting and ending points are alinged in at least 2 direction of the space
      ((and (= x 0)
           (or (= y 0) (= z 0))
           )
        1
        )
      ((and (= y 0) (= z 0))
       1
       )
      ; exclude negative distances
      ((or (< x 0) (< y 0) (< z 0))
       0
       )
      (else            
        ; recursive step
        (+
         (manhattan-3d (- x 1) y z) ; I move one step horizontally
         (manhattan-3d x (- y 1) z) ; or I move one step vertically
         (manhattan-3d x y (- z 1)) ; or I move one step deeply
         )
        )
     )
    ))

;; test

(manhattan-3d 0 0 7) ; 1
(manhattan-3d 2 0 2) ; 6
(manhattan-3d 1 1 1) ; 6
(manhattan-3d 1 1 5) ; 42
(manhattan-3d 2 3 1) ; 60
(manhattan-3d 2 3 3) ; 560