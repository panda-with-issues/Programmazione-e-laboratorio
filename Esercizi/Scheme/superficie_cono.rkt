;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname superficie_cono) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define ^2                               ; positive squared n
  (lambda (n)                            ; n: real number
   (* n n)
  )
)
(define S-base                           ; real positive number
  (lambda (r)                            ; r(adius): real positive number; 
    (* pi (^2 r))
  )
)
(define S-lat                            ; real positive number
  (lambda (r h)                          ; r(adius) and h(height): real positive number
    (* 2 pi r (sqrt (+ (^2 r) (^2 h))))
  )
)
(define S-tot                            ; real positive number
  (lambda (r h)                          ; r(adius) and h(height): real positive number
    (+ (S-base r) (S-lat r h))
  )
)